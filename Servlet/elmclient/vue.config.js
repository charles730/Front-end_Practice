module.exports = {
    devServer: {
        proxy: {
            '/elm': {
                target: 'http://localhost:8080',
                changeOrigin: true,
            }
        }
    }
}