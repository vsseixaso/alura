import React, { useState } from 'react'
import { Button, FormControlLabel, Switch, TextField } from '@material-ui/core'

function PersonalData({ handleSubmit, validations }) {
  const [name, setName] = useState('')
  const [lastname, setLastname] = useState('')
  const [cpf, setCpf] = useState('')
  const [promotions, setPromotions] = useState(true)
  const [news, setNews] = useState(true)
  const [errors, setErrors] = useState({ cpf: { valid: true, text: '' } })

  function validFields(event) {
    const { name, value } = event.target
    const newState = { ...errors }
    newState[name] = validations[name](value)
    setErrors(newState)
  }

  return (
    <form
      onSubmit={event => {
        event.preventDefault()
        handleSubmit({ name, lastname, cpf, promotions, news })
      }}
    >
      <TextField
        id="name"
        label="Nome"
        value={name}
        onChange={event => {
          setName(event.target.value)
        }}
        variant="outlined"
        margin="normal"
        fullWidth
      />
      <TextField
        id="lastname"
        label="Sobrenome"
        value={lastname}
        onChange={event => {
          setLastname(event.target.value)
        }}
        variant="outlined"
        margin="normal"
        fullWidth
      />
      <TextField
        id="cpf"
        label="CPF"
        name="cpf"
        value={cpf}
        onChange={event => {
          setCpf(event.target.value)
        }}
        onBlur={validFields}
        error={!errors.cpf.valid}
        helperText={errors.cpf.text}
        variant="outlined"
        margin="normal"
        fullWidth
      />

      <FormControlLabel
        label="Promoções"
        control={
          <Switch
            checked={promotions}
            onChange={event => {
              setPromotions(event.target.checked)
            }}
            name="promotions"
            color="primary"
          />
        }
      />
      <FormControlLabel
        label="Novidades"
        control={
          <Switch
            checked={news}
            onChange={event => {
              setNews(event.target.checked)
            }}
            name="news"
            color="primary"
          />
        }
      />

      <Button type="submit" variant="contained" color="primary">
        Cadastrar
      </Button>
    </form>
  )
}

export default PersonalData
