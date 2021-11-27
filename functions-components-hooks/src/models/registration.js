function validCPF(cpf) {
  return cpf.length !== 11
    ? { valid: false, text: 'CPF deve ter 11 dígitos.' }
    : { valid: true, text: '' }
}

function validPassword(password) {
  return password.length < 4 || password.length > 72
    ? { valid: false, text: 'Senha deve ter entre 4 e 72 dígitos.' }
    : { valid: true, text: '' }
}

export { validCPF, validPassword }
