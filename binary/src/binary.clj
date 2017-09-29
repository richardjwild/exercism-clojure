(ns binary)

(defn double-then-if-digit-is-one-add-1 [accumulator digit]
  (+
    (* accumulator 2)
    (if (= \1 digit) 1 0)))

(defn to-decimal [b]
  (reduce double-then-if-digit-is-one-add-1 0 b))
