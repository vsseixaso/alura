import { Button, TextField } from '@material-ui/core'
import React, { useState } from 'react'

function UserData({ handleSubmit }) {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  return (
    <form
      onSubmit={event => {
        event.preventDefault()
        handleSubmit({ email, password })
      }}
    >
      <TextField
        id="email"
        label="E-mail"
        value={email}
        onChange={event => setEmail(event.target.value)}
        type="email"
        required
        variant="outlined"
        margin="normal"
        fullWidth
      />
      <TextField
        id="password"
        label="Senha"
        value={password}
        onChange={event => setPassword(event.target.value)}
        type="password"
        required
        variant="outlined"
        margin="normal"
        fullWidth
      />
      <Button type="submit" variant="contained" color="primary">
        Cadastrar
      </Button>
    </form>
  )
}

export default UserData
