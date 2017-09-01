(ns robot-name)

(def next-ident (ref 0))
(def robots (ref {}))

(defn next-key []
  (if-let [robot-keys (keys @robots)]
    (inc (apply max robot-keys))
    0))

(defn robot []
  (dosync
    (alter next-ident inc)
    (let [robot-key (next-key)]
      (alter robots assoc robot-key @next-ident)
      robot-key)))

(defn reset-name [robot-key]
  (dosync
    (alter next-ident inc)
    (alter robots assoc robot-key @next-ident)))

(defn robot-name [robot-key]
  (format "AA0%03d" (get @robots robot-key)))
