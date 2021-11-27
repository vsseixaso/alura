import { Step, StepLabel, Stepper, Typography } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import AddressData from './AddressData'
import PersonalData from './PersonalData'
import UserData from './UserData'

function RegistrationForm({ handleSubmit, validations }) {
  const [currentStep, setCurrentStep] = useState(0)
  const [collectedData, setData] = useState({})

  useEffect(() => {
    if (currentStep === forms.length-1) handleSubmit(collectedData)
  })

  const forms = [
    <UserData handleSubmit={collectData} validations={validations} />,
    <PersonalData handleSubmit={collectData} validations={validations} />,
    <AddressData handleSubmit={collectData} validations={validations} />,
    <Typography variant="h5">Obrigado pelo Cadastro!</Typography>
  ]

  function collectData(data) {
    setData({ ...collectedData, ...data })
    nextStep()
  }

  function nextStep() {
    setCurrentStep(currentStep + 1)
  }

  return (
    <>
      <Stepper activeStep={currentStep}>
        <Step><StepLabel>Login</StepLabel></Step>
        <Step><StepLabel>Pessoal</StepLabel></Step>
        <Step><StepLabel>Endereço</StepLabel></Step>
        <Step><StepLabel>Finalização</StepLabel></Step>
      </Stepper>
      {forms[currentStep]}
    </>
  )
}

export default RegistrationForm
