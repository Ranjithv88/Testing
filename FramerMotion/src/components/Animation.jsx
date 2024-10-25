import React from 'react'
import './Animation.scss'
import { motion } from "framer-motion"

function Animation() {
  return (
    <div className='outer'>
      <motion.div className='inner' initial={{ x: '-50%', y: '-50%'}} animate={{ rotate: 360 }} transition={{
        type: "spring",
        stiffness: 50,
        damping: 30,
        repeat: Infinity,
        repeatType: "loop", 
        duration: 1}}
      ></motion.div>
    </div>
  )
}

export default Animation

