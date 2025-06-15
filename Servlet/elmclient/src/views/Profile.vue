<template>
  <div class="profile-container">
    <!-- 用户信息卡片 -->
    <div class="user-card">
      <div class="avatar-container">
        <img :src="avatarSrc" alt="用户头像" class="avatar">
      </div>
      <div class="user-info">
        <h2>{{ user.name }}</h2>
        <p class="phone">{{ formatPhone(user.phone) }}</p>
      </div>
    </div>

    <!-- 退出登录按钮 -->
    <div class="logout-section">
      <button class="logout-btn" @click="logout">
        退出登录
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Profile',
  data() {
    return {
      user: {
        name: '游客',
        phone: '',
        avatar: ''
      },

    };
  },
  created() {
    // 从sessionStorage获取用户信息
    this.loadUserData();
  },
  computed: {
    // 计算属性返回头像URL
    avatarSrc() {
      return this.user.avatar || this.defaultAvatar;
    }
  },
  methods: {
    // 格式化电话号码显示
    formatPhone(phone) {
      if (!phone) return '';
      if (phone.length !== 11) return phone;
      return `${phone.substring(0, 3)}****${phone.substring(7)}`;
    },

    // 加载用户数据
    loadUserData() {
      const userData = sessionStorage.getItem('user');
      if (userData) {
        try {
          const parsedData = JSON.parse(userData);
          this.user = {
            name: parsedData.name || '用户',
            phone: parsedData.phone || '',
            avatar: parsedData.avatar || ''
          };
        } catch (e) {
          console.error('解析用户数据错误:', e);
        }
      } else {
        // 如果未登录，则跳转到登录页面
        this.$router.replace('/login');
      }
    },

    // 退出登录方法
    logout() {
      // 清空sessionStorage中的用户信息
      sessionStorage.removeItem('user');

      // 显示退出成功提示
      this.showToast('已退出登录');

      // 延迟跳转到登录页
      setTimeout(() => {
        this.$router.replace('/login');
      }, 800);
    },

    // 显示Toast提示
    showToast(message) {
      // 移除可能存在的旧提示
      const existingToast = document.querySelector('.toast');
      if (existingToast) existingToast.remove();

      // 创建新提示元素
      const toast = document.createElement('div');
      toast.className = 'toast';
      toast.textContent = message;

      // 添加到文档中
      document.body.appendChild(toast);

      // 添加显示动画
      setTimeout(() => toast.classList.add('show'), 10);

      // 3秒后移除提示
      setTimeout(() => {
        toast.classList.remove('show');
        setTimeout(() => toast.remove(), 300);
      }, 3000);
    }
  }
}
</script>

<style scoped>
.profile-container {
  padding: 20px;
  display: flex;
  flex-direction: column;
  height: 100vh;
  background-color: #f8f8f8;
}

.user-card {
  background: white;
  border-radius: 16px;
  padding: 30px 20px;
  margin-top: 30px;
  display: flex;
  align-items: center;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
  transition: all 0.3s ease;
}

.user-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.12);
}

.avatar-container {
  width: 80px;
  height: 80px;
  margin-right: 20px;
  border-radius: 50%;
  overflow: hidden;
  background: linear-gradient(45deg, #ff7e5f, #feb47b);
  padding: 5px;
}

.avatar {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  border: 3px solid white;
  object-fit: cover;
}

.user-info h2 {
  font-size: 24px;
  color: #333;
  margin-bottom: 8px;
  font-weight: 600;
}

.user-info .phone {
  font-size: 16px;
  color: #666;
  letter-spacing: 0.5px;
}

.logout-section {
  margin-top: 40px;
  display: flex;
  justify-content: center;
}

.logout-btn {
  padding: 15px 45px;
  background: linear-gradient(to right, #ff7e5f, #feb47b);
  color: white;
  font-size: 18px;
  font-weight: 600;
  border: none;
  border-radius: 30px;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 4px 15px rgba(254, 180, 123, 0.4);
}

.logout-btn:hover {
  transform: scale(1.05);
  box-shadow: 0 6px 20px rgba(254, 180, 123, 0.6);
}

.logout-btn:active {
  transform: scale(0.98);
}

/* Toast提示样式 */
.toast {
  position: fixed;
  bottom: 40px;
  left: 50%;
  transform: translateX(-50%) translateY(100px);
  background: rgba(0, 0, 0, 0.7);
  color: white;
  padding: 12px 24px;
  border-radius: 30px;
  font-size: 16px;
  font-weight: 500;
  opacity: 0;
  transition: all 0.3s ease;
  white-space: nowrap;
  z-index: 1000;
}

.toast.show {
  transform: translateX(-50%) translateY(0);
  opacity: 1;
}
</style>