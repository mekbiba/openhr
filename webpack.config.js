var path = require('path')
var webpack = require('webpack')
const VueLoaderPlugin = require('vue-loader/lib/plugin')

function resolve (dir) {
    return path.join(__dirname, '..', dir)
}

module.exports = {
    entry: './frontend/main.js',
    output: {
        path: __dirname + "/build/resources/main/static",
        filename: "js/bundle.js"
    },
    resolve: {
        extensions: ['.js', '.vue', '.json'],
        alias: {
            'vue$': 'vue/dist/vue.esm.js',
            '@': resolve('src')
        }
    },
    module: {
        rules: [
            {
                test: /\.vue$/,
                loader: 'vue-loader'
            },
            {
                test: /\.js$/,
                loader: 'babel-loader',
                exclude: /node_modules/,
                include: [
                    resolve('src'),
                    resolve('node_modules/vue-strap')
                ]
            },
            {
                test: /\.html$/,
                loader: 'vue-html-compiler'
            },
            {
                test: /\.(jpe?g|png|gif)$/i,   //to support eg. background-image property
                loader:"file-loader",
                query:{
                    name:'[name].[ext]',
                    outputPath:'images/'
                    //the images will be emmited to public/assets/images/ folder
                    //the images will be put in the DOM <style> tag as eg. background: url(assets/images/image.png);
                }
            },
            {
                test: /\.(woff(2)?|ttf|eot|svg)(\?v=[0-9]\.[0-9]\.[0-9])?$/,    //to support @font-face rule
                loader: "url-loader",
                query:{
                    limit:'10000',
                    name:'[name].[ext]',
                    outputPath:'fonts/'
                    //the fonts will be emmited to public/assets/fonts/ folder
                    //the fonts will be put in the DOM <style> tag as eg. @font-face{ src:url(assets/fonts/font.ttf); }
                }
            },
            {
                test: /\.css$/,
                loaders: ["style-loader","css-loader"]
            }
        ]
    },
    plugins: [
        new VueLoaderPlugin()
    ]
};
