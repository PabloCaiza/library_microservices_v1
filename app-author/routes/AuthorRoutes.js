const {Router} = require('express')
const {Author} = require('../schema/Author')
const validateFields = require('../middleware/ValidarCampos')
const {check} = require("express-validator");
const {where} = require("sequelize");
const router = Router()

const authorExist = async (id) => {
    const author = await Author.findOne({
        where:{
            id:id
        }
    })
    if (!author)
        throw new Error(`Author with id ${id} doesn't exist`)

}
router.get('/', async (req, res) => {
    const authors = await Author.findAll()
    res.json(authors)


})

router.get('/:id', [
    check("id").custom(authorExist),
    validateFields

], async (req, res) => {
    const {id} = req.params
    const author = await Author.findOne({
        where:{
            id:id
        }
    })
    res.json(author)

})

router.post('/', [
    check('first_name').not().isEmpty(),
    check('last_name').not().isEmpty(),
    validateFields
], async (req, res) => {
    const {first_name, last_name} = req.body;
    await Author.create({first_name, last_name})
    return res.status(201).json()

})

router.delete("/:id",
    [
        check("id").custom(authorExist),
        validateFields],
    async (req, res) => {
        const {id} = req.params
        await Author.destroy({
            where:{
                id:id
            }
        })
        res.status(204).json()

    })

router.put("/:id", [
    check("id").custom(authorExist),
    validateFields
], async (req, res) => {
    const {id} = req.params
    const {_id, first_name,last_name} = req.body
    await Author.update({first_name,last_name},{
        where:{
            id:id
        }
    })
    res.status(204).json()
})


module.exports = router