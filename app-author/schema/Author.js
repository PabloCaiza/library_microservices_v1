const {DataType, DataTypes}=require('sequelize')
const {sequelize}=require('../config/DatabaseConfig')

const Author=sequelize.define('author',{
    id:{
        type: DataTypes.INTEGER,
        autoIncrement:true,
        primaryKey:true

    },
    first_name:{
        type: DataTypes.STRING
    },
    last_name:{
        type:DataTypes.STRING
    }
},{
    createdAt:false,
    updatedAt:false
})
module.exports={
    Author
}