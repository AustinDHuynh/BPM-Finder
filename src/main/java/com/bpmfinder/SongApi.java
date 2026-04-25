package com.bpmfinder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

import com.google.gson.Gson;
import java.util.ArrayList;




public class SongApi {
    private final String baseURI = "https://customer.api.soundcharts.com";
    private final static String sandbox = "soundcharts";
    private String apiKey = sandbox;
    private String appID = sandbox;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    private final Gson gson = new Gson();

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
        System.out.println("API key updated successfully.");
    }
    public void setAppID(String appID) {
        this.appID = appID;
        System.out.println("App ID updated successfully.");
    }



    public Song searchSong(String uuid){

        String searchURI = baseURI + "/api/v2.25/song/" + uuid;

        System.out.println("Final Request URI: " + searchURI);

        HttpRequest request = buildRequest(searchURI);

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                SongListResponse data = gson.fromJson(response.body(), SongListResponse.class);
                if(data != null && data.getItems() != null && !data.getItems().isEmpty()){
                    Song song = data.getItems().getFirst();
                    loadAudioFeatures(song);
                    return song;
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Song> getSearchList(String query) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8).replace("+", "%20");
        String requestURI = baseURI + "/api/v2/song/search/" + encodedQuery;
        HttpRequest request = buildRequest(requestURI);

        if(apiKey.equals(sandbox) || appID.equals(sandbox) ){
            System.out.println("WARNING: You are using sandbox credentials, which will not show all songs.");
        }

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                SongListResponse data = gson.fromJson(response.body(), SongListResponse.class);
                return data.getItems();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private HttpRequest buildRequest(String uri) {
        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .headers("x-app-id", appID, "x-api-key", apiKey)
                .GET()
                .build();
    }

    public void loadAudioFeatures(Song song) {
        if (song == null || song.getUuid() == null) return;

        String requestSongDetailsURI = baseURI + "/api/v2.25/song/" + song.getUuid();
        HttpRequest request = buildRequest(requestSongDetailsURI);

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                SingleSongResponse data = gson.fromJson(response.body(), SingleSongResponse.class);
                if (data != null && data.getObject() != null) {
                    song.setAudio(data.getObject().getAudio());
                }
            } else {
                System.out.println("Failed to get more details. HTTP Code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    class SingleSongResponse {
        private Song object;

        public Song getObject() {
            return object;
        }
    }
    class SongListResponse {
        ArrayList<Song> items;

        public ArrayList<Song> getItems() {
            return items;
        }
    }
}
