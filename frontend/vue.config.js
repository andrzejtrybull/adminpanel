module.exports = {
    devServer: {
        '^/api': {
            target: 'http://localhost:8080',
            ws: true,
            changeOrigin: true
        }
    }
};
