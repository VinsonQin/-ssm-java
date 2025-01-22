// 加载指定页面的内容到右侧内容展示栏
function loadContent(pageUrl) {
    const content = document.getElementById('content');
    content.innerHTML = `<iframe src="${pageUrl}" frameborder="0" style="width: 100%; height: 100%;"></iframe>`;
}

// 页面加载完成后，默认显示 index.html
window.onload = function() {
    loadContent('post/index');
};

// 获取当前日期和星期
function getCurrentDate() {
    const daysOfWeek = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
    const now = new Date();
    const year = now.getFullYear();
    const month = now.getMonth() + 1; // 月份从0开始，需加1
    const date = now.getDate();
    const day = daysOfWeek[now.getDay()];
    return `${year}年${month}月${date}日 ${day}`;
}

// 根据时间段返回问候语
function getGreeting() {
    const now = new Date();
    const hours = now.getHours();

    if (hours >= 5 && hours < 9) {
        return '早上好！';
    } else if (hours >= 9 && hours <14){
        return '中午好！';
    } else if (hours >= 14 && hours < 18) {
        return '下午好！';
    } else if (hours >= 18 && hours < 22) {
        return '晚上好！';
    } else {
        return '深夜好！注意休息哦！';
    }
}

// 动态设置日期和问候语
function updatePageContent() {
    const dateElement = document.getElementById('date');
    const greetingElement = document.getElementById('greeting');

    dateElement.textContent = getCurrentDate();
    greetingElement.textContent = getGreeting();
}

// 初始化
updatePageContent();

