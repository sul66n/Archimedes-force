    $(document).ready(function () {
    const ball1 = $(".ball1");
    const container = $(".container");
    const containerWidth = container.width();
    const containerHeight = container.height();
    const ballWidth = ball1.outerWidth();
    const ballHeight = ball1.outerHeight();
    const water = $(".water");
    const ballRadius = ballHeight / 2;
    const centerPositionX = (containerWidth - ballWidth) / 2;

    ball1.css({ left: centerPositionX });
    let currentBallTop = (containerHeight - ballHeight) / 2;
    ball1.css({ top: currentBallTop });

    const initialWaterHeight = containerHeight - ballRadius / 3 * 2 - 50;
    water.css("height", initialWaterHeight);

    $("#archimedesForm").on("submit", function (event) {
    event.preventDefault();

    const density = parseFloat($("#density").val());
    const volume = parseFloat($("#volume").val());
    const mass = parseFloat($("#mass").val());

    $.ajax({
    url: "/archimed",
    type: "POST",
    data: { density, volume, mass },
    success: function (response) {
    const archimedesForce = response.archimedForce;
    const gravityForce = response.gravityForce;
    $("#archimedesForce").text("Сила Архимеда: " + archimedesForce.toFixed(2) + " Н");
    $("#gravityForce").text("Силя тяжести: " + gravityForce.toFixed(2) + " Н");

    const blueIntensity = Math.min(Math.floor(density / 20), 209);
    water.css("background-color", `rgb(0, ${210 - blueIntensity}, 255)`);
    animateBall(archimedesForce, gravityForce);
},
    error: function () {
    alert("Error calculating Archimedes' force.");
}
});
});

    // Динамическая проверка значений на введение в поле
    $("#density, #volume, #mass").on("input", function () {
    const value = $(this).val();
    if (value.startsWith("0") && value.length > 1) {
    $(this).addClass("invalid");
} else {
    $(this).removeClass("invalid");
}
});

    function createBubbles(count = 15) {
    for (let i = 0; i < count; i++) {
    const bubble = $('<div class="bubble"></div>');
    bubble.css({
    left: Math.random() * 580 + "px",
    bottom: "0px"
});
    container.append(bubble);
    bubble.animate({ bottom: "500px", opacity: 0 }, 1500, function () {
    bubble.remove();
});
}
}

    function animateBall(archimedesForce, gravityForce) {
    const forceDifference = archimedesForce - gravityForce;
    const epsilon = 0.01;

    let finalPositionY = currentBallTop; // Start with the current position
    let finalWaterHeight = water.height();

    if (forceDifference > epsilon) {
    finalPositionY = 50;
    finalWaterHeight = containerHeight - ballRadius - 50;
} else if (forceDifference < -epsilon) {
    finalPositionY = containerHeight - ballHeight;
    finalWaterHeight = containerHeight - ballRadius / 3 * 2 - 50;
}

    createBubbles();

    water.stop().animate({ height: finalWaterHeight }, 2000, "easeInOutQuad");

    if (Math.abs(forceDifference) > epsilon) {
    ball1.stop().animate({ top: finalPositionY }, 2000, "easeOutBounce", function () {
    currentBallTop = finalPositionY;
    ball1.animate({ top: finalPositionY - 10 }, 300)
    .animate({ top: finalPositionY }, 300)
    .animate({ top: finalPositionY - 5 }, 200)
    .animate({ top: finalPositionY }, 200);
});
}
}
});