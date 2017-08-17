(ns rna-transcription)

(def base-pairs {\C, "G",
                 \G, "C",
                 \A, "U",
                 \T, "A"})

(defn get-pair [base]
  (if-let [pair (get base-pairs base)]
    pair
    (throw (AssertionError. "No matching base pair"))))

(defn to-rna [to-transcribe]
  (apply str (map get-pair to-transcribe)))
