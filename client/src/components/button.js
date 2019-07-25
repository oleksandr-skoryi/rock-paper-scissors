import React from 'react';

export const Button = ({ onClick, children, className }) => {
    return <button onClick={() => onClick ? onClick() : null} className={className}>
        {children}
    </button>
}