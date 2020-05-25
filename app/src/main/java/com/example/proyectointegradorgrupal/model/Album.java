package com.example.proyectointegradorgrupal.model;

import java.util.List;

public class Album {
    private Integer id;
    private String title;
    private String link;
    private String cover;
    private Integer duration;
    private Integer rating;
    private String tracklist;
    private AlbumTracks tracks;


    public Album() {

    }

    public AlbumTracks getTracks() {
        return tracks;
    }

    public void setTracks(AlbumTracks tracks) {
        this.tracks = tracks;
    }

    public Album(Integer id, String title, String link, String cover, Integer duration, Integer rating, String tracklist, AlbumTracks tracks) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.cover = cover;
        this.duration = duration;
        this.rating = rating;
        this.tracklist = tracklist;
        this.tracks = tracks;


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    public class AlbumTracks {
        private List<Track> data;


        public AlbumTracks() {

        }

        public AlbumTracks(List<AlbumTracks.Track> data) {
            this.data = data;
        }

        public List<AlbumTracks.Track> getData() {
            return data;
        }

        public void setData(List<AlbumTracks.Track> data) {
            this.data = data;
        }

        public class Track {
            private String id;
            private String title;
            private String titleShort;
            private String link;
            private Integer duration;
            private Integer rank;
            private String explicitLyrics;
            private String preview;


            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTitleShort() {
                return titleShort;
            }

            public void setTitleShort(String titleShort) {
                this.titleShort = titleShort;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public Integer getDuration() {
                return duration;
            }

            public void setDuration(Integer duration) {
                this.duration = duration;
            }

            public Integer getRank() {
                return rank;
            }

            public void setRank(Integer rank) {
                this.rank = rank;
            }

            public String getExplicitLyrics() {
                return explicitLyrics;
            }

            public void setExplicitLyrics(String explicitLyrics) {
                this.explicitLyrics = explicitLyrics;
            }

            public String getPreview() {
                return preview;
            }

            public void setPreview(String preview) {
                this.preview = preview;
            }


        }
    }
}

