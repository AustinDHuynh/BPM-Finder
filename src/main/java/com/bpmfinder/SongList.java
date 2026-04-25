package com.bpmfinder;

import java.util.ArrayList;


public class SongList {
    private ArrayList<Song> songs = new ArrayList<>();

    public void updateList(ArrayList<Song> newSongs) {
        this.songs = (newSongs != null) ? newSongs : new ArrayList<>();
    }

    public void printList() {
        if (songs.isEmpty()) {
            System.out.println("No songs found.");
            return;
        }
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ": " + songs.get(i));
        }
    }

    public Song getByIndex(int index) {
        if (index > 0 && index <= songs.size()) {
            return songs.get(index - 1);
        }
        return null;
    }
    public int getSize(){
        return songs.size();
    }
}