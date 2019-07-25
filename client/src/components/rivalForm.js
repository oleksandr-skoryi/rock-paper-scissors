import './rivalForm.scss';
import React from 'react';
import { Button } from './button';

export const RivalForm = ({ buttons, skynetChoice }) => {
    return <div className='button-group rival-gropup'>
        {buttons.map((it, index) => {
            const classes = skynetChoice === it.value ? 'button_active' : '';
            const classesRound = skynetChoice ? 'button_disable' : '';
            return <Button className={`button ${classes} ${classesRound} `} key={index}>
                <img src={it.img} alt={it.alt} />
            </Button>
        })}
    </div>
}