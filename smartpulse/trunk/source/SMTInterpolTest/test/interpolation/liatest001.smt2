(set-option :print-success false)
(set-option :produce-proofs true)
(set-option :interpolant-check-mode true)
(set-option :verbosity 3)
(set-logic QF_LIA)
(declare-fun x ( ) Int)
(declare-fun y ( ) Int)
(declare-fun z ( ) Int)
(assert (! (= (* 2 x) y) :named a1) )
(assert (! (= (+ (* 2 z) 1) y) :named a2) )
(check-sat)
(get-interpolants a1 a2)
