package com.tjohnn.jokeprovider;

import java.util.Random;

public class JokeProvider {

    private static String[] jokes = new String[]{
            "The best thing about a boolean is even if you are wrong, you are only off by a bit. ",
            "One man’s crappy software is another man’s full time job. ",
            "A good programmer is someone who always looks both ways before crossing a one-way street. ",
            "Programming is like sex. One mistake and you have to support it for the rest of your life.",
            "If debugging is the process of removing software bugs, then programming must be the process of putting them in. ",
    };

    public static String getJoke(){

        return jokes[new Random().nextInt(jokes.length -1)];
    }

}
