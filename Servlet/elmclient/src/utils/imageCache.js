// 保存用户头像到本地缓存
export async function saveUserImage(userId, imageUrl) {
    try {
        const cachedImage = localStorage.getItem(`user_${userId}_image`);
        if (cachedImage) return;

        // 从URL获取图片数据
        const response = await fetch(imageUrl);
        if (!response.ok) {
            throw new Error(`Failed to fetch image: ${response.status}`);
        }

        // 转换为Base64格式存储
        const blob = await response.blob();
        const reader = new FileReader();

        return new Promise((resolve, reject) => {
            reader.onloadend = () => {
                localStorage.setItem(`user_${userId}_image`, reader.result);
                resolve();
            };

            reader.onerror = reject;
            reader.readAsDataURL(blob);
        });
    } catch (error) {
        console.error('保存用户头像失败', error);
        throw error;
    }
}

// 从本地缓存加载用户头像
export function loadUserImage(userId) {
    try {
        return localStorage.getItem(`user_${userId}_image`);
    } catch (error) {
        console.error('加载用户头像失败', error);
        return null;
    }
}

// 清除用户头像缓存
export function clearUserImage(userId) {
    try {
        localStorage.removeItem(`user_${userId}_image`);
    } catch (error) {
        console.error('清除用户头像缓存失败', error);
    }
}