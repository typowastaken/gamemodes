package com.typocreates.gamemodes.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.typocreates.gamemodes.Gamemodes;
import com.typocreates.gamemodes.data.UpdateCheckResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class UpdateChecker {

    private final Gamemodes plugin;

    public UpdateChecker(Gamemodes plugin) {
        this.plugin = plugin;
    }

    public void checkUpdate() {
        Logger logger = plugin.getLogger();
        logger.info("Checking for updates...");
        CompletableFuture<UpdateCheckResponse> future = getLatestVersion();

        future.thenAccept((data) -> {
            if (data.getStatusCode() == 410) {
                logger.warning("==============================================================================================");
                logger.warning("The Modrinth API returned status 410! (The API for the update checker!)");
                logger.warning("This means a major update has occurred to the API which requires an update.");
                logger.warning("If you don't mind a few errors, this is likely fine. The update checker will be broken though.");
                logger.warning("To fix this, please check if an update is available for this plugin:");
                logger.warning("https://modrinth.com/project/CD4bmArk");
                logger.warning("==============================================================================================");
                return;
            }

            if (!data.getLatestVersionNumber().equals(plugin.getDescription().getVersion())) {
                logger.info("=====================================================================");
                logger.info("A new version is available for download!");
                logger.info("Update information:");
                logger.info(" ");
                logger.info(data.getLatestVersionTitle());
                for (String line : data.getSplitChangeLog()) {
                    logger.info(line);
                }
                logger.info(" ");
                logger.info("Download the update here: https://modrinth.com/project/CD4bmArk");
                logger.info("=====================================================================");
            } else {
                logger.info("Update check complete, no updates needed!");
            }
        });
    }

    public CompletableFuture<UpdateCheckResponse> getLatestVersion() {
        Logger logger = plugin.getLogger();
        URI uri = URI.create("https://api.modrinth.com/v2/project/CD4bmArk/version?include_changelog=true&version_type=release");

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).orTimeout(5, TimeUnit.SECONDS);
            response.exceptionally((e) -> {
                logger.warning("Unable to check for updates! Do you have an internet connection?");
                logger.warning(e.getMessage());
                return null;
            });
            return response.thenApply((r) -> {
                Gson gson = new Gson();
                JsonArray jsonResponse = gson.fromJson(r.body(), JsonArray.class);

                if (jsonResponse.isEmpty()) {
                    return null;
                }

                JsonObject latestVersion = jsonResponse.get(0).getAsJsonObject();
                String latestVersionNumber = latestVersion.get("version_number").getAsString();
                String versionTitle = latestVersion.get("name").getAsString();
                String changeLog = latestVersion.get("changelog").getAsString();

                String[] splitChangeLog = changeLog
                        .split("\\n");

                return new UpdateCheckResponse(r.statusCode(), latestVersionNumber, versionTitle, changeLog, splitChangeLog);
            });
        }
    }
}