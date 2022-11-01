(ns introducao-clojure.core
  (:gen-class))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; * Capitulo 1
;; Primeiros contatos com clojure
;; As primeiras linhas de codigo

(+ 1 2)
(* 2 3)
(/ 2 2)
(- 0 2)
(* 2 (+ 3 3))
(str "Oi, " "Mundo!")
(= "Oi" "Ola")
(= "Oi" "Oi")
(even? 2)
(odd? 1)
(= 0 (mod 9 3))
(zero? (mod 9 3))

;; Nossas primeiras funcoes

(defn oi [nome]
  (str "Oi, " nome))

(oi "Bruno!")

(defn multiplo-de-3? [numero]
  (zero? (mod numero 3)))

(multiplo-de-3? 3)
(multiplo-de-3? 6)
(multiplo-de-3? 7)

;; O que eh verdade? Sobre condicionais

(defn par? [numero]
  (if (even? numero)
    "Sim"
    "Nao"))

(par? 5)
(par? 10)

(defn saldo [valor]
  (cond
    (< valor 0) "Negativo"
    (> valor 0) "Positivo"
    (= valor 0) "Zero"))

(saldo -1)
(saldo 1)
(saldo 0)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; * Capitulo 2
;; Resolvendo FizzBuzz

;; Fizz, se o numero for divisivel 3;
;; Buzz, se o numero for divisivel 5;
;; FizzBuzz, se o numero for divisivel por 3 e 5;
;; Se nao atender nenhuma condicao, retorna o proprio numero

(defn eh-divisivel? [dividendo divisor]
  (zero? (mod dividendo divisor)))

(defn fizzbuzz [numero]
  (cond
    (and
     (eh-divisivel? numero 3)
     (eh-divisivel? numero 5)) "FizzBuzz"
    (eh-divisivel? numero 3) "Fizz"
    (eh-divisivel? numero 5) "Buzz"
    :else numero))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; * Capitulo 3
;; Estrutura de dados em clojure

;; Listas
(list 1 2 3 4 5)
'(1 2 3 4 5)

(def um-ate-5 '(1 2 3 4 5))
(count um-ate-5)
(def um-ate-15 (range 1 16))

;; FizzBuzz de 1 ate 15
(map fizzbuzz um-ate-15)

;; Vetores
(vector 1 2 3 4 5)
[1 2 3 4 5]

(def numeros-vetorizados [1 2 3 4 5])
(map fizzbuzz numeros-vetorizados)

(def cantor-arretado (vector "Chico Cezar" "Catole da Rocha" 26 "janeiro" 1964))

(get cantor-arretado 0)
(get cantor-arretado 4)
(last cantor-arretado)
(first cantor-arretado)
(second cantor-arretado)

(conj cantor-arretado "MPB")

(def cantora-arretada (list "Renata Arruda" "Joao Pessoa" 23 "Dezembro" 1967))
(nth cantora-arretada 0)
(nth cantora-arretada 4)
(last cantora-arretada)
(first cantora-arretada)
(conj cantora-arretada "MPB")

;; Sets
(hash-set "Chico Cezar" "Renata Arruda")
#{"Chico Cezar" "Renata Arruda"}
(def artistas #{"Chico Cezar" "Renata Arruda"})
(conj artistas "Jackson do Pandeiro")
(conj artistas "Chico Cezar")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; * Capitulo 4
;; Controle financeiro e novas estruturas de dados

;; Funcionalidades
;; - Registrar transacoes de receita e despezas
;; - Agrupar transacoes por categoria (alimentacao, lazer, moradia)
;; - Agrupar transacoes por periodo
;; - Agrupar transacoes por rotulo (tags como desnecessaria, inesperada e cara)

;; Keywords
:a

;; Mapas
(hash-map :valor 200 :tipo "receita")
(def transacao {:valor 200 :tipo "receita"})

(assoc transacao :categoria "educacao")
(get transacao :tipo)

(:valor transacao)
(def transacao-desnecessaria {:valor 34
                              :tipo "despesa"
                              :rotulos '("desnecessaria" "cartao")})

(:rotulos transacao-desnecessaria)
(:rotulos transacao)
(:rotulos transacao '())

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; * Capitulo 5
;; Programacao funcional, o comeco

;; Funcoes: primeira classe e grandeza superior
;; Funcoes de grandeza superior e nossas financas

(defn resumo [transacoes]
  (select-keys transacoes [:tipo :valor :data]))

(def transacoes [{:valor 33.0   :tipo "despesa" :comentario "almoco" :data "19/11/2016"}
                 {:valor 2700.0 :tipo "receita" :comentario "bico" :data "01/12/2016"}
                 {:valor 29.0   :tipo "despesa" :comentario "livro de clojure" :data "03/12/2016"}])

(map resumo transacoes)

;; Filtrando transacoes

(defn despesa? [transacao]
  (= (:tipo transacao) "despesa"))

(filter despesa? transacoes)

;; Condensando resultado de iteracoes

(defn so-valor [transacao]
  (:valor transacao))

(map so-valor (filter despesa? transacoes))
(reduce + (map so-valor (filter despesa? transacoes)))

;; Funcoes anonimas

(defn valor-grande [transacao]
  (> (:valor transacao) 100))

(filter valor-grande transacoes)

(def impares '(1 3 5 7 9))
(def ano-do-pentacampeonato-do-brasil 2002)
(def pi 3.14)
(def um-terco 1/3)

((fn [nome] (str "Ola, " nome "!")) "mundo novo")

(def ola (fn [nome] (str "Ola, " nome "!")))
(ola "mundo novo")

(filter (fn [transacao] (> (:valor transacao) 100)) transacoes)
(filter #(> (:valor %) 100) transacoes)

(reduce + (map #(:valor %) (filter #(= (:tipo %) "despesa") transacoes)))

;; Lendo codigo clojure de uma outra forma

(reduce +
        (map so-valor
             (filter despesa? transacoes)))

(so-valor (first transacoes))
(-> (first transacoes)
    so-valor)

(->> (filter despesa? transacoes)
     (map so-valor)
     (reduce +))

;; Capitulo 6
;; Composicao de funcoes e aplicacao parcial de funcoes


