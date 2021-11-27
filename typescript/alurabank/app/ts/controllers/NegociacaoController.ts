import { domInject, throttle } from '../helpers/decorators/index';
import { Negociacao, NegociacaoParcial, Negociacoes } from "../models/index";
import { MensagemView, NegociacoesView } from "../views/index";
import { NegociacaoService } from '../services/index';
import { imprime } from '../helpers/index';

export class NegociacaoController {

  @domInject('#data')
  private _inputData: JQuery;
  
  @domInject('#quantidade')
  private _inputQuantidade: JQuery;

  @domInject('#valor')
  private _inputValor: JQuery;

  private _negociacoes = new Negociacoes();
  private _negociacoesView = new NegociacoesView('#negociacoesView', true);
  private _mensagenmView = new MensagemView('#mensagemView');
  private _service = new NegociacaoService();

  constructor() {
    this._negociacoesView.update(this._negociacoes);
  }

  @throttle()
  adiciona() {
    let data = new Date(this._inputData.val().replace(/-/g, ','));

    if (!this._ehDiaUtil(data)) {
      this._mensagenmView.update('Somente negociações em dias úteis!');
      return;
    }

    const negociacao = new Negociacao(
      data,
      parseInt(this._inputQuantidade.val()),
      parseFloat(this._inputValor.val())
    );

    this._negociacoes.adiciona(negociacao);
    this._negociacoesView.update(this._negociacoes);
    this._mensagenmView.update('Negociação adicionada com sucesso!');

    imprime(negociacao, this._negociacoes);
  }

  private _ehDiaUtil(data: Date): boolean {
    return data.getDay() != DiaDaSemana.Sabado && data.getDay() != DiaDaSemana.Domingo;
  }

  @throttle()
  async importaDados() {
    try {
      const negociacoesParaImportar = await this._service
        .obterNegociacoes(res => {
          if (res.ok) return res;
          else throw new Error(res.statusText);
        });

      const negociacoesJaImportadas = this._negociacoes.paraArray();

      negociacoesParaImportar
        .filter(negociacao => 
          !negociacoesJaImportadas.some(jaImportada => 
            negociacao.ehIgual(jaImportada)))
        .forEach(negociacao => 
          this._negociacoes.adiciona(negociacao));
      
      this._negociacoesView.update(this._negociacoes);
    } catch (err) {
      this._mensagenmView.update(err.message);
    }
  }
}

enum DiaDaSemana {
  Domingo,
  Segunda,
  Terça,
  Quarta,
  Quinta,
  Sexta,
  Sabado
}