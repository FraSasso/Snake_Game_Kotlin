class PlayerInfo {
    var fullName = ""
    var id = 0
    var userPw = ""
    var username = ""

    companion object {
        fun findPlayerInfo(username: String): PlayerInfo? {
            connection.use { conn ->
                conn.prepareStatement("select * from user where login = ?").use { ps ->
                    ps.setString(1, username)
                    ps.executeQuery().use { res ->
                        if (res.next()) {
                            val playerInfo = PlayerInfo()
                            playerInfo.fullName = res.getString("fullname")
                            playerInfo.username = res.getString("login")
                            playerInfo.id = res.getInt("id")
                            playerInfo.userPw = res.getString("password")
                            return playerInfo
                        }
                    }
                }
            }
            return null
        }
    }
}

//            connection.use { conn ->
//                val preparedStatement = conn.prepareStatement("select * from user where login = ?")
//                preparedStatement.setString(1, username)
//                val res = preparedStatement.executeQuery()
//                res.use {
//                    if (res.next()) {
//                        val playerInfo = PlayerInfo()
//                        playerInfo.fullName = res.getString("fullname")
//                        playerInfo.username = res.getString("login")
//                        playerInfo.id = res.getInt("id")
//                        playerInfo.userPw = res.getString("password")
//                        return playerInfo
//                    }
//                }
//            }
