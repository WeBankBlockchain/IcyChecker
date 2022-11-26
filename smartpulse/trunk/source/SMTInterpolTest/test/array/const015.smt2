(set-option :produce-proofs true)
(set-option :proof-check-mode true)

(set-logic QF_ALIA)
(declare-sort U 0)
(declare-fun i () Int)
(declare-fun j () Int)
(declare-fun v2 () U)
(declare-fun a () (Array Int U))
(declare-fun b () (Array Int U))
(define-fun constU ((v U)) (Array Int U) ((as const (Array Int U)) v))

(assert (= (constU (select a i)) (store  a j v2)))
(assert (= i j))
(assert (not (= a (constU (select a i)))))
(check-sat)
(get-proof)
(exit)