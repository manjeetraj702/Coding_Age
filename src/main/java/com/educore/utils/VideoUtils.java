package com.educore.utils;

import com.educore.exception.ApplicationException;

import java.net.URI;
import java.net.URISyntaxException;

public class VideoUtils {

    // Extract Video ID from URL
    public static String extractVideoId(String videoUrl) {
        try {
            URI uri = new URI(videoUrl);
            String query = uri.getQuery();
            for (String param : query.split("&")) {
                if (param.startsWith("v=")) {
                    return param.split("=")[1];
                }
            }
        } catch (URISyntaxException e) {
            throw new ApplicationException("Something wrong in URL");
        }
        return null;
    }

    // Generate Thumbnail URL
    public static String generateThumbnailUrl(String videoUrl) {
        String videoId = extractVideoId(videoUrl);
        return videoId != null ? "https://img.youtube.com/vi/" + videoId + "/maxresdefault.jpg" : null;
    }
}

