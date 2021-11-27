System.register([], function (exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    function logarTempoDeExecucao(emSegundos = false) {
        return function (target, key, descriptor) {
            const metodoOriginal = descriptor.value;
            descriptor.value = function (...args) {
                let unidade = emSegundos ? 's' : 'ms';
                let divisor = emSegundos ? 1000 : 1;
                console.log('-----------------------');
                console.log(`Parâmetros do método ${key}: ${JSON.stringify(args)}`);
                const t1 = performance.now();
                const resultado = metodoOriginal.apply(this, args);
                const t2 = performance.now();
                console.log(`Resultado do método: ${JSON.stringify(resultado)}`);
                console.log(`${key} demorou ${(t2 - t1) / divisor} ${unidade}`);
                console.log('-----------------------');
                return resultado;
            };
            return descriptor;
        };
    }
    exports_1("logarTempoDeExecucao", logarTempoDeExecucao);
    return {
        setters: [],
        execute: function () {
        }
    };
});
