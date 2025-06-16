<!-- components/PaymentSuccess.vue -->
<template>
  <div class="success-container">
    <div class="icon-wrapper">
      <i class="fa fa-check-circle"></i>
    </div>
    <h3>支付成功</h3>
    <p>订单已支付，感谢您的购买！</p>
    <button @click="goHome">返回首页</button>
  </div>
</template>

<script>
export default {
  name: 'PaymentSuccess',
  methods: {
    goHome() {
      this.$router.push({ name: 'Home' });
    }
  }
}
</script>

<style scoped>
.success-container {
  padding: 20vw 4vw;
  text-align: center;
}
.icon-wrapper i {
  font-size: 16vw;
  color: #38CA73;
}
h3 {
  font-size: 6vw;
  margin: 4vw 0;
}
p {
  font-size: 4vw;
  color: #666;
  margin-bottom: 8vw;
}
button {
  width: 80%;
  height: 12vw;
  margin: 2vw auto;
  border: none;
  border-radius: 4px;
  background-color: #38CA73;
  color: #fff;
  font-size: 4vw;
}
button:last-child {
  background-color: #fff;
  color: #38CA73;
  border: 1px solid #38CA73;
}
</style>