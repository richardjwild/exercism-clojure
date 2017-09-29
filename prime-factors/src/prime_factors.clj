(ns prime-factors)

(defn factor? [n candidate]
  (zero? (rem n candidate)))

(defn divide [number candidate factors-so-far]
  (loop [factors factors-so-far, next-number number]
    (if (factor? next-number candidate)
      (recur (conj factors candidate) (/ next-number candidate))
      (vector factors next-number))))

(defn of [number]
  (loop [candidate 2, n number, factors-so-far []]
    (let [[factors next-number] (divide n candidate factors-so-far)]
      (if (> next-number 1)
        (recur (inc candidate) next-number factors)
        factors))))