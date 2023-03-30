package com.example.videogame

class GameData {
    companion object VideoGame {
        fun getAll() :List<Game> {
            return listOf(
                Game(
                    "FIFA 23",
                    "PlayStation/PC/Nintendo Switch/Xbox",
                    "27.09.2022",
                    2.0,
                    "",
                    "6.0",
                    "EA Sports",
                    "EA",
                    "Sports",
                    "FIFA 23 features both men's and women's World Cup game modes, replicating the 2022 FIFA World Cup and the 2023 FIFA Women's World Cup.",
                    mutableListOf()
                ),
                Game(
                    "Minecraft", "PlayStation/PC/Nintendo Switch/Xbox", "18.11.2011", 8.6,
                    "", "E10+", "Mojang Studios", "Mojang Studios", "Sandbox",
                    "In Minecraft, players explore a blocky, procedurally generated, three-dimensional world with virtually infinite terrain and may discover and extract raw materials, craft tools and items, and build structures, earthworks, and machines.",
                    mutableListOf()
                ),
                Game(
                    "World of Warcraft",
                    "PC",
                    "23.11.2004",
                    3.8,
                    "",
                    "T10+",
                    "Blizzard Entertainment",
                    "Blizzard Entertainment",
                    "Action",
                    "Set in the fictional world of Azeroth, WoW allows players to create avatar-style characters and explore a sprawling universe while interacting with nonreal players—called nonplayer characters (NPCs)—and other real-world players (PCs)",
                    mutableListOf()
                ),
                Game(
                    "Fortnite",
                    "PlayStation/PC/Nintendo Switch/Xbox",
                    "21.07.2017",
                    8.0,
                    "",
                    "T13+",
                    "Epic Games",
                    "Epic Games",
                    "Battle royale",
                    "In Fortnite, players collaborate to survive in an open-world environment, by battling other characters who are controlled either by the game itself, or by other players.",
                    mutableListOf()
                ),
                Game(
                    "F1 22",
                    "PlayStation/PC/Xbox",
                    "28.06.2022",
                    2.4,
                    "",
                    "E8+",
                    "Codemasters",
                    "EA Sports",
                    "Racing",
                    "F1 22 is a racing video game that simulates the official Formula 1 championship. It is the official game of the Formula 1",
                    mutableListOf()
                ),
                Game(
                    "GTA V",
                    "PlayStation/PC/Xbox",
                    "17.09.2013",
                    4.4,
                    "",
                    "T18+",
                    "Rockstar Games",
                    "Rockstar Games",
                    "Third-person action game",
                    "Grand Theft Auto V is an action-adventure game played from either a third-person or first-person perspective. Players complete missions—linear scenarios with set objectives—to progress through the story. Outside of the missions, players may freely roam the open world.",
                    mutableListOf()
                ),
                Game(
                    "Call Of Duty - Black Ops: Cold War",
                    "PlayStation/PC/Xbox",
                    "13.11.2020",
                    2.2,
                    "",
                    "M18+",
                    "Activison",
                    "Activision",
                    "Action",
                    "Call of Duty: Black Ops Cold War is set during the Cold War in the early 1980s. The campaign follows Green Beret turned CIA SAD/SOG officer Russell Adler (Bruce Thomas) and his mission to stop an international espionage threat named Perseus (William Salyers) in 1981.",
                    mutableListOf()
                ),
                Game(
                    "Hitman 3",
                    "PlayStation/PC/Xbox",
                    "20.01.2021",
                    4.4,
                    "",
                    "M17+",
                    "IO Interactive",
                    "IO Interactive",
                    "Action",
                    "Hitman 3 is a stealth game played from a third-person perspective and players once again assume control of assassin Agent 47.",
                    mutableListOf()
                ),
                Game(
                    "NBA 2K23",
                    "PlayStation/PC/Xbox",
                    "08.09.2022",
                    2.3,
                    "",
                    "E4+",
                    "Visual Concepts",
                    "2K Sports",
                    "Sport",
                    "NBA 2K23 is a 2022 basketball based on the National Basketball Association (NBA).",
                    mutableListOf()
                ),
                Game(
                    "League of Legends",
                    "PC",
                    "27.10.2009",
                    3.0,
                    "",
                    "T13+",
                    "Riot Games",
                    "Riot Games",
                    "Multiplayer online battle arena",
                    "League of Legends is one of the world's most popular video games, developed by Riot Games. It features a team-based competitive game mode based on strategy and outplaying opponents. Players work with their team to break the enemy Nexus before the enemy team breaks theirs.",
                    mutableListOf()
                )
            )
        }
        fun getDetails(title: String) :Game? {
            var games = getAll()
            for (i in 0..9) {
                if(games[i].title == title) return games[i];
            }
            return null;
        }
    }
}