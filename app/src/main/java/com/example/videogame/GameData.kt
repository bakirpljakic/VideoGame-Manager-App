package com.example.videogame

import java.sql.Timestamp
import java.time.LocalDateTime

class GameData {
    companion object VideoGame {
        fun getAll(): List<Game> {
            return listOf(
                Game(
                    "Need for Speed Underground 2",
                    "PlayStation/Xbox/PC",
                    "09.11.2004",
                    8.7 ,
                    "",
                    "E",
                    "EA Black Box",
                    "Electronic Arts (EA)", "Racing" ,
                    "Need for Speed Underground 2 is a popular racing video game that was released in 2004. " +
                            "In Need for Speed Underground 2, players take on the role of a street racer who must climb the ranks of the underground racing scene in a fictional city called Bayview. ",
                    mutableListOf(
                        UserRating("bpljakic", 1652306400000, 3.0),
                        UserRating("khalilovic", 1625781600000, 3.5),
                        UserReview("ebronja", 1664229600000, "Fantastic racing game that I would highly recommend to anyone looking for some high-speed action."),
                        UserReview("eagovic", 1664575200000, "I was really disappointed with this racing game."),
                        UserRating("smalkic", 1661378400000, 5.0))
                ),
                Game(
                    "Zula",
                    "PC",
                    "19.09.2016",
                    6.2,
                    "",
                    "12+",
                    "MadByte Games",
                    "Lokum Games",
                    "Action",
                    "Zula is a free-to-play first-person shooter game with a focus on multiplayer battles. " +
                            "It features a range of gameplay modes, including team deathmatch, capture the flag, and free for all. " +
                            "The game also has a variety of playable characters with unique abilities and customizable weapons.",
                    mutableListOf(
                        UserRating("john", 1652306400000, 4.0),
                        UserReview("adedic", 1625851600000, "I've been playing Zula for a while now and I have to say, it's one of the best free-to-play FPS games out there."),
                        UserRating("esalkic", 1657387600000, 3.5),
                        UserReview("abasic", 1664575200000, "I had high hopes for Zula, but unfortunately, it fell short of my expectations."),
                        UserRating("shukic", 1657906000000, 5.0))
                ),
                Game(
                    "World of Tanks",
                    "PlayStation/Xbox/PC",
                    "12.08.2010.",
                    7.7,
                    "",
                    "T",
                    "Wargaming",
                    "Wargaming",
                    "Vehicular combat",
                    "World of Tanks is a popular online multiplayer game that features tanks from various nations and historical periods. " +
                            "Players can choose from a variety of tanks and participate in battles against other players, completing objectives and earning rewards.",
                    mutableListOf()
                ),
                Game(
                    "PES 2022",
                    "PlayStation/Xbox/PC",
                    "30.09.2022",
                    7.9,
                    "",
                    "E",
                    "Konami Digital Entertainment",
                    "Konami Digital Entertainment",
                    "Sports",
                    "PES 2022 is a football/soccer game developed and published by Konami." +
                            " It features many licensed clubs and leagues, as well as gameplay improvements such as improved ball control, dribbling, and AI." +
                            " It also has a new mode called Match Pass, which allows players to earn rewards by completing objectives during matches.",
                    mutableListOf()
                ),
                Game(
                    "Grand Theft Auto V",
                    "PlayStation/Xbox/PC",
                    "17.09.2013",
                    9.0,
                    "",
                    "M",
                    "Rockstar North",
                    "Rockstar North",
                    "Action-adventure",
                    "Grand Theft Auto V (GTA V) is an open-world action-adventure game developed by Rockstar North and published by Rockstar Games." +
                            " The game is set in the fictional city of Los Santos, which is based on Los Angeles, and its surrounding areas." +
                            " The game features a single-player story mode, as well as an online multiplayer mode.",
                    mutableListOf()
                ),

                Game(
                    "Gran Turismo Sport",
                    "PlayStation",
                    "17.10.2017",
                    6.9,
                    "",
                    "E",
                    "Polyphony Digital",
                    "Sony Interactive Entertainment",
                    "Racing",
                    "Gran Turismo Sport is a racing video game that features high-quality graphics and realistic physics simulation." +
                            " The game was designed to be an online-focused experience, with an emphasis on competitive racing and esports.",
                    mutableListOf()
                ),
                Game(
                    "The Forest",
                    "PlayStation/PC",
                    "30.05.2014",
                    7.4,
                    "",
                    "M",
                    "Endnight Games",
                    " Endnight Games",
                    "Survival",
                    "The Forest takes place on a remote, heavily forested peninsula where the player character has survived a plane crash. " +
                            "The player must scavenge for food and supplies, build shelter, and fend off attacks from mutants that inhabit the forest. " +
                            "The game features both single-player and co-operative multiplayer modes.",
                    mutableListOf()
                ),

                Game(
                    "Minecraft",
                    "PlayStation/Xbox/PC",
                    "17.05.2009",
                    8.3,
                    "",
                    "E10+",
                    "Mojang Studios",
                    "Mojang Studios",
                    "Sandbox",
                    "Minecraft is a sandbox video game developed by Mojang Studios. In the game, players explore a blocky, procedurally-generated 3D world, mining resources and using them to craft tools, buildings, and other objects. ",
                    mutableListOf()
                ),
                Game(
                    "Euro Truck Simulator 2",
                    "PC",
                    "19.10.2012",
                    9.0,
                    "",
                    "E",
                    "SCS Software",
                    "SCS Software",
                    "Simulation",
                    "Euro Truck Simulator is a truck driving simulation game developed and published by SCS Software. " +
                            "The game allows players to drive trucks across Europe, picking up and delivering cargo while following traffic laws and managing their own business. " +
                            "The game features realistic physics, detailed graphics, and a variety of trucks to choose from.",
                    mutableListOf()
                ),
                Game(
                    "Red Dead Redemption 2",
                    "PlayStation/Xbox/PC",
                    "26.10.2018",
                    9.5,
                    "",
                    "M",
                    " Rockstar Studios",
                    " Rockstar Studios",
                    "Action-adventure",
                    "Red Dead Redemption 2 is an action-adventure game set in the late 1800s, in a fictionalized version of the American Old West. " +
                            "The game follows the story of Arthur Morgan, a member of the Van der Linde gang, as he navigates the challenges of life on the run after a failed heist. ",
                    mutableListOf()
                )
            )
        }

        fun getDetails(game_title: String): Game? {
            var games = getAll()
            for (i in 0..9) {
                if (games[i].title == game_title) return games[i]
            }
            return null
        }
    }
}