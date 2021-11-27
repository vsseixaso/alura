import { Button, TextField } from '@material-ui/core'
import React, { useState } from 'react'

function AddressData({ handleSubmit }) {
  const [cep, setCep] = useState('')
  const [street, setStreet] = useState('')
  const [number, setNumber] = useState('')
  const [state, setState] = useState('')
  const [city, setCity] = useState('')

  return (
    <form
      onSubmit={event => {
        event.preventDefault()
        handleSubmit({ cep, street, number, state, city })
      }}
    >
      <TextField
        id="cep"
        label="CEP"
        value={cep}
        onChange={event => setCep(event.target.value)}
        type="number"
        variant="outlined"
        margin="normal"
      />
      <TextField
        id="street"
        label="Endereço"
        value={street}
        onChange={event => setStreet(event.target.value)}
        type="text"
        variant="outlined"
        margin="normal"
        fullWidth
      />
      <TextField
        id="number"
        label="Número"
        value={number}
        onChange={event => setNumber(event.target.value)}
        type="number"
        variant="outlined"
        margin="normal"
      />
      <TextField
        id="state"
        label="Estado"
        value={state}
        onChange={event => setState(event.target.value)}
        type="text"
        variant="outlined"
        margin="normal"
      />
      <TextField
        id="City"
        label="Cidade"
        value={city}
        onChange={event => setCity(event.target.value)}
        type="text"
        variant="outlined"
        margin="normal"
      />
      <Button type="submit" variant="contained" color="primary" fullWidth>
        Finalizar Cadastro
      </Button>
    </form>
  )
}

export default AddressData
