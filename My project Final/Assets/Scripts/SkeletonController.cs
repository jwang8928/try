using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SkeletonController : Navigator
{
    public GameObject[] waypoints; 
    private Animator animator;
    private int currentWaypoint = 0;


    // Start is called before the first frame update
    new void Start()
    {
        base.Start();
        animator = GetComponent<Animator>();
        SetTarget(waypoints[currentWaypoint]);
    }

    // Update is called once per frame
    new void Update()
    {    
        base.Update();
        
    }

    protected override void OnReachTarget(GameObject target)
    {
        //arrive at waypoint
        if (target == waypoints[currentWaypoint])
        {
            currentWaypoint++;
            if (currentWaypoint == waypoints.Length) 
            {
                currentWaypoint = 0; 
            }
            SetTarget(waypoints[currentWaypoint]);
        }
        //arrive player
        else if (target.gameObject.CompareTag("Player"))
        {
            animator.SetBool("Attacking",true);
        }

    }

    private void OnTriggerEnter(Collider collision)
    {
        if (collision.gameObject.CompareTag("Player"))
        {
            SetTarget(collision.gameObject);
            animator.SetBool("Running",true);

        }
    }

    private void OnTriggerExit(Collider other)
    {
        if (other.gameObject.CompareTag("Player"))
        {
            SetTarget(waypoints[currentWaypoint]);
            animator.SetBool("Running", false);
            animator.SetBool("Attacking", false);


        }
    }
    public void GetHit()
    {
        animator.SetBool("Dead", true);
        SetTarget(null);
    }
}
