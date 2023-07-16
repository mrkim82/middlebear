let selectedButton = null;
		
function toggleButtonStyle(button) {
  if (selectedButton !== button) {
    if (selectedButton) {
      resetButtonStyle(selectedButton);
    }
    
    let className = button.classList.item(1);

    if (className.startsWith('btn-outline')) {
      let filledClassName = className.replace('btn-outline', 'btn');
      button.classList.remove(className);
      button.classList.add(filledClassName);
    } else {
      let outlineClassName = className.replace('btn', 'btn-outline');
      button.classList.remove(className);
      button.classList.add(outlineClassName);
    }

    selectedButton = button;
  } else {
    resetButtonStyle(button);
    selectedButton = null;
  }
}

//버튼 초기화
function resetButtonStyle(button) {
  let className = button.classList.item(1);
  
  if (className.startsWith('btn-outline')) {
    let outlineClassName = className.replace('btn-outline', 'btn');
    button.classList.remove(className);
    button.classList.add(outlineClassName);
  } else {
    let filledClassName = className.replace('btn', 'btn-outline');
    button.classList.remove(className);
    button.classList.add(filledClassName);
  }
}