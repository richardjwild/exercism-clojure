(ns nucleotide_count)

(def all-bases #{\A \C \G \T})

(defn count [base sequence]
  (assert (contains? all-bases base))
  (get (frequencies sequence) base 0))

(defn nucleotide-counts [sequence]
  (apply hash-map (mapcat #(list %1 (count %1 sequence)) all-bases)))