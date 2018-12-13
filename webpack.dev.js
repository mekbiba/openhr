const webpack = require('webpack')
const merge = require('webpack-merge')
const common = require('./webpack.config.js')


const LiveReloadPlugin = require('webpack-livereload-plugin')

module.exports = merge(common, {
    mode: 'development',
    watch: true,
    devtool: 'source-map',
    plugins: [
        new LiveReloadPlugin({port:35731}),
        new webpack.DefinePlugin({ // <-- key to reducing React's size
            'process.env': {
                'NODE_ENV': JSON.stringify('development')
            }
        }),
    ]
});
