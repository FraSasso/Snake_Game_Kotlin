class Score {
    var time = 0.0
    var apples = 0

    companion object {
        fun saveScore(appleScore: String, timeScore: String, pInfo: PlayerInfo) {
            connection.use { conn ->
                val sql = "insert into games (apples,time,ownerid) values(?,?,?)"
                conn.prepareStatement(sql).use { ps ->
                    ps.setString(1, appleScore)
                    ps.setString(2, timeScore)
                    ps.setInt(3, pInfo.id)
                    ps.execute()
                }
            }
        }

        fun orderedTopScores(pInfo: PlayerInfo): List<Score> {
            connection.use { conn ->
                conn.prepareStatement(
                    "select * from games where ownerid = ? order by apples desc, time asc limit 5"
                ).use { ps ->
                    ps.setInt(1, pInfo.id)
                    ps.executeQuery().use { res ->
                        val list = mutableListOf<Score>()
                        while (res.next()) {
                            val s = Score()
                            s.time = res.getDouble("time")
                            s.apples = res.getInt("apples")
                            list.add(s)
                        }
                        return list
                    }
                }
            }
        }
    }

}
