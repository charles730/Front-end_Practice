module.exports = {
    devServer: {
        proxy: {
            '/elm': {
                target: 'http://localhost:8080', // Tomcat地址
                changeOrigin: true,
            }
        }
    }
}