package com.bpmfinder;

import com.google.gson.annotations.SerializedName;

public class Song {
    private String uuid;
    private String name;
    @SerializedName("creditName")
    private String artist;

    private String releaseDate;
    private Audio audio;

    public static class Audio {
        private double tempo;
        private double timeSignature;

        public Audio(double tempo, double timeSignature) {
            this.tempo = tempo;
            this.timeSignature = timeSignature;
        }

        public double getTempo() {
            return tempo;
        }

        public double getTimeSignature() {
            return timeSignature;
        }
    }


    public Song(String uuid, String name, String creditName, Audio audio) {
        this.uuid = uuid;
        this.name = name;
        this.artist = creditName;
        this.audio = audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    public Audio getAudio() {
        return audio;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getShortDate() {
        if (releaseDate != null && releaseDate.contains("T")) {
            return releaseDate.split("T")[0];
        }
        return releaseDate != null ? releaseDate : "Unknown Date";
    }

    @Override
    public String toString() {
        return String.format("%-30s | %-15s", name, artist);
    }

    private String truncate(String value, int length) {
        if (value == null) return "Unknown";
        if (value.length() > length) {
            return value.substring(0, length - 3) + "...";
        }
        return value;
    }

    public String returnAllDetailsString() {
        String bpm = (audio != null) ? String.format("%.2f", audio.getTempo()) : "N/A";
        String timeSig = (audio != null) ? (int) audio.getTimeSignature() + "/4" : "N/A";


        return String.format(
                "--------------------------------%n" +
                "      DETAILED SONG INFO        %n" +
                "--------------------------------%n" +
                "%-15s %s%n" +
                "%-15s %s%n" +
                "%-15s %s%n" +
                "--------------------------------%n" +
                "%-15s %s%n" +
                "%-15s %s%n" +
                "--------------------------------",
                "Title:", (name != null ? name : "Unknown"),
                "Artist:", (artist != null ? artist : "Unknown"),
                "Release Date:", getShortDate(),
                "BPM:", bpm,
                "Time Signature:", timeSig
        );
    }

}