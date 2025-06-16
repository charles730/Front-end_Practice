<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>在线支付</p>
    </header>
    <!-- 订单信息部分 -->
    <h3>订单信息：</h3>
    <div class="order-info">
      <p>
        {{ orders.business.businessName }}
        <i class="fa fa-caret-down" @click="detailetShow"></i>
      </p>
      <p>&#165;{{ orders.orderTotal }}</p>
    </div>
    <!-- 订单明细部分 -->
    <ul class="order-detailet" v-show="isShowDetailet">
      <li v-for="item in orders.list">
        <p>{{ item.food.foodName }} x {{ item.quantity }}</p>
        <p>&#165;{{ item.food.foodPrice * item.quantity }}</p>
      </li>
      <li>
        <p>配送费</p>
        <p>&#165;{{ orders.business.deliveryPrice }}</p>
      </li>
    </ul>
    <!-- 支付方式部分 -->
    <ul class="payment-type">
      <li>
        <img src="../assets/alipay.png" alt="使用支付宝">
      </li>
      <li>
        <img src="../assets/wechat.png" alt="使用微信支付">
        <i class="fa fa-check-circle"></i>
      </li>
    </ul>
    <div class="payment-button">
      <button @click="handlePayment">确认支付</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Payment',
  created() {
    this.$axios.post('OrdersController/getOrdersById', this.$qs.stringify({
      orderId: this.orderId
    })).then(response => {
      this.orders = response.data;
    }).catch(error => {
      console.error(error);
      alert('加载订单信息失败');
    });
  },
  mounted() {
    // 防止返回订单确认页面
    history.pushState(null, null, document.URL);
    window.onpopstate = () => {
      this.$router.push({path: '/index'});
    };
  },
  destroyed() {
    window.onpopstate = null;
  },
  methods: {
    detailetShow() {
      this.isShowDetailet = !this.isShowDetailet;
    },
    handlePayment() {
      // 显示加载状态
      this.isLoading = true;

      // 调用实际支付接口
      this.$axios.post('OrdersController/confirmOrdersById', this.$qs.stringify({
        orderId: this.orderId
      }))
          .then(response => {
            // 根据接口返回值判断支付结果
            if (response.data === 1) {
              // 支付成功，跳转到成功页面
              this.$router.push({
                name: 'PaymentSuccess',
                query: {orderId: this.orderId}
              });
            } else {
              // 支付失败，跳转到失败页面
              this.$router.push({
                name: 'PaymentFailure',
                query: {
                  orderId: this.orderId,
                  message: '支付失败，请重试'
                }
              });
            }
          })
          .catch(error => {
            console.error('支付接口调用失败', error);
            // 支付异常，跳转到失败页面
            this.$router.push({
              name: 'PaymentFailure',
              query: {
                orderId: this.orderId,
                message: '支付异常，请稍后重试'
              }
            });
          })
          .finally(() => {
            this.isLoading = false;
          });
    }
  },
  data() {
    return {
      orderId: this.$route.query.orderId,
      orders: {business: {}},
      isShowDetailet: false,
      isLoading: false // 加载状态
    }
  }
}
</script>

<style scoped>
.wrapper .payment-button button.loading {
  background-color: #ccc;
  cursor: not-allowed;
}

.wrapper .payment-button button.loading::after {
  content: ' ';
  display: inline-block;
  width: 4vw;
  height: 4vw;
  margin-left: 2vw;
  border: 2px solid #fff;
  border-radius: 50%;
  border-top-color: transparent;
  animation: spin 1s linear infinite;
  vertical-align: middle;
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}

/****************** 总容器 ******************/
.wrapper {
  width: 100%;
  height: 100%;
}

/****************** header部分 ******************/
.wrapper header {
  width: 100%;
  height: 12vw;
  background-color: #0097FF;
  color: #fff;
  font-size: 4.8vw;
  position: fixed;
  left: 0;
  top: 0;
  z-index: 1000;
  display: flex;
  justify-content: center;
  align-items: center;
}

/****************** 订单信息部分 ******************/
.wrapper h3 {
  margin-top: 12vw;
  box-sizing: border-box;
  padding: 4vw 4vw 0;
  font-size: 4vw;
  font-weight: 300;
  color: #999;
}

.wrapper .order-info {
  box-sizing: border-box;
  padding: 4vw;
  font-size: 4vw;
  color: #666;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .order-info p:last-child {
  color: orangered;
}

/****************** 订单明细部分 ******************/
.wrapper .order-detailet {
  width: 100%;
}

.wrapper .order-detailet li {
  width: 100%;
  box-sizing: border-box;
  padding: 1vw 4vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .order-detailet li p {
  font-size: 3vw;
  color: #666;
}

/****************** 支付方式部分 ******************/
.wrapper .payment-type {
  width: 100%;
}

.wrapper .payment-type li {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.wrapper .payment-type li img {
  width: 33vw;
  height: 8.9vw;
}

.wrapper .payment-type li .fa-check-circle {
  font-size: 5vw;
  color: #38CA73;
}

.wrapper .payment-button {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw;
  z-index: 10;
}

.wrapper .payment-button button {
  width: 100%;
  height: 10vw;
  border: none;
  outline: none;
  border-radius: 4px;
  background-color: #38CA73;
  color: #fff;
}
</style>