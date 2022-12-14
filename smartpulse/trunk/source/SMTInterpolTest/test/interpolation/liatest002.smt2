(set-option :produce-proofs true)
(set-option :interpolant-check-mode true)
(set-logic QF_LIA)
(declare-fun a () Int)
(declare-fun b () Int)
(assert (! (distinct a b) :named IP1))
(assert (! (<= a b) :named IP2))
(assert (! (>= a b) :named IP3))
(check-sat)
(get-interpolants IP1 IP2 IP3)
(get-interpolants IP3 IP2 IP1)
(get-interpolants IP3 IP1 IP2)
(get-interpolants IP1 IP3 IP2)
(get-interpolants IP2 IP3 IP1)
(get-interpolants IP2 IP1 IP3)
(exit)
