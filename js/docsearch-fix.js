let originalDocsearch = null;

Object.defineProperty(window, 'docsearch', {
  get: function() {
    return function(config) {
      if (originalDocsearch) {
        try {
          const container = typeof config.container === 'string' 
            ? document.querySelector(config.container) 
            : config.container;
          
          if (container) {
            return originalDocsearch(config);
          }
        } catch (error) {
          // Silently ignore errors
        }
      }
    };
  },
  set: function(value) {
    originalDocsearch = value;
  },
  configurable: true
});
