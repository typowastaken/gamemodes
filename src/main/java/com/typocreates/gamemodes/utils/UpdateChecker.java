package com.typocreates.gamemodes.utils;

import com.typocreates.gamemodes.Gamemodes;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class UpdateChecker {

    private final Gamemodes plugin;
    private final String projectId;

    public UpdateChecker(Gamemodes plugin, String projectId) {
        this.plugin = plugin;
        this.projectId = projectId;
    }

    public void checkUpdate() {
        Logger logger = plugin.getLogger();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.modrinth.com/v2/project/{id}/version".replace("{id}", projectId)))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(logger::info)
                .join();
    }
}