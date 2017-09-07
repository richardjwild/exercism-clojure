(ns sublist)

(defn l-within-r [l r]
  (and (map = l r)))

(defn classify [a b]
  (if (= a b)
    :equal
    (if (< (count a) (count b))
      (if (l-within-r a b) :sublist :unequal)
      (if (l-within-r b a) :superlist :unequal))))