(set-option :produce-interpolants true)
(set-option :interpolant-check-mode true)
(set-logic QF_UF)
(declare-sort Int 0)
(declare-fun a () Int)
(declare-fun b () Int)
(declare-fun c () Int)
(declare-fun p () Bool)
(assert (! true :named root))
(assert (! (and (= a b) (=> p (distinct a c))) :named left))
(assert (! (and (= b c) (=> (not p) (distinct a c))) :named right))
(check-sat)
(get-interpolants left (right) root)
