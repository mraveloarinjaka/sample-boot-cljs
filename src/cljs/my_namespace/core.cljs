;; create the main project namespace
(ns my-namespace.core
  (:require [my-namespace.login :as l]))

;; enable cljs to print to the JS console of the browser
(enable-console-print!)

;; print to the console
(println "Hello, new world!")

;; initialize the HTML page in unobtrusive way
(set! (.-onload js/window) l/init)

#_ (js/alert "42")
#_ (js/console.log {:answer 42} #js{:answer 42})

(comment
  (str (.-value (.getElementById js/document "email")))
  (set! (.-value (.getElementById js/document "email")) "fool@dummy.inc")
  (set! (.-value (.getElementById js/document "password")) "fool_dummy_inc"))
