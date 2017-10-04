(ns atbash-cipher
  (require [clojure.string :as str]))

(def alphabet "abcdefghijklmnopqrstuvwxyz")
(def numbers "0123456789")
(def encoding (merge (zipmap numbers numbers) (zipmap alphabet (reverse alphabet))))

(defn encode-characters [lowercase-plaintext]
  (map #(encoding %) lowercase-plaintext))

(defn remove-nils [seq]
  (remove nil? seq))

(defn interpose-space [char-seq]
  (interpose \  char-seq))

(defn to-string [seq]
  (reduce str seq))

(defn group-5 [char-seq]
  (map #(to-string %) (partition-all 5 char-seq)))

(defn encode [plaintext]
  (-> plaintext
      str/lower-case
      encode-characters
      remove-nils
      group-5
      interpose-space
      to-string))
