(ns bank-account)

(defn open-account []
  (atom {:open true, :balance 0}))

(defn get-balance [account]
  (when (@account :open)
    (@account :balance)))

(defn update-balance [account credit-amount]
  (when (@account :open)
    (swap! account assoc :balance
           (+ (@account :balance) credit-amount))))

(defn close-account [account]
  (swap! account assoc :open false))