const express = require('express')
const cors = require('cors')
const DbMigrate = require('db-migrate')
const {sequelize}=require('../config/DatabaseConfig')

class Server {

    constructor() {
        this.app = express()
        this.port = process.env.SERVER_PORT || 3000;
        this.middleware()
        this.routes()
        this.migrate()
        this.connectDatabase()

    }

    async connectDatabase() {
        console.log(process.env.DB_USER)
        await sequelize.authenticate()
    }

    async migrate() {
        const dbmigrate = DbMigrate.getInstance(true);
        await dbmigrate.up()
    }

    middleware() {
        this.app.use(cors())
        this.app.use(express.json())

    }

    routes() {
        this.app.use("/authors", require('../routes/AuthorRoutes'))
    }

    listen() {
        this.app.listen(this.port, () => console.log(`Server is running on port ${this.port}`))
    }
}

module.exports = Server