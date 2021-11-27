import React from 'react'
import { Container, Typography } from '@material-ui/core'
import RegistrationForm from './components/RegistrationForm/RegistrationForm'
import 'fontsource-roboto'
import { validCPF, validPassword } from './models/registration'

function App() {
  return (
    <Container component="article" maxWidth="sm">
      <Typography variant="h3" component="h1" align="center">
        Formulário de cadastro
      </Typography>
      <RegistrationForm
        handleSubmit={handleSubmitForm}
        validations={{ cpf: validCPF, password: validPassword }}
      />
    </Container>
  )
}

function handleSubmitForm(data) {
  console.log(data)
}

export default App
