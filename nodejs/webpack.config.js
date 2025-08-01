// webpack.config.js
const path = require('path');

module.exports = {
  entry: './index.js',          
  target: 'node',                    
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: 'bundle.js'
  },
  mode: 'production',
  module: {
    rules: [
      {
        test: /\.json$/,
        type: 'json' // Webpack 5+ le gère automatiquement
      }
    ]
  },
  resolve: {
    extensions: ['.js', '.json']
  }
};