(ns my-namespace.bootstrap)

(defn jetty-init
  []
  (.put (System/getProperties)
        "org.eclipse.jetty.servlet.Default.useFileMappedBuffer"
        "false"))


